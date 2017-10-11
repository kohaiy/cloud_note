<template>
  <div class="main">
    <div id="book-list" class="book-list">
      <v-book-list v-on:addBook="showAddBook" v-on:updateBook="showUpdateBook" v-on:deleteBook="deleteBook"
                   :bookList="bookList"></v-book-list>
    </div>

    <div id="note-list" class="note-list">
      <v-note-list v-on:clickNote="clickNote"></v-note-list>
    </div>

    <div class="editor">
      <v-editor :editableTabs2="openNote"></v-editor>
    </div>



    <el-dialog title="修改笔记本" :visible.sync="updateBookDialogVisible" :size="dialogSize">
      <el-form :model="updateBook" ref="updateBook" :rules="updateRules">
        <el-form-item prop="bookName">
          <el-input v-model="updateBook.bookName" placeholder="笔记本名称..."></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="updateBook.bookDesc" type="textarea" placeholder="输入笔记本的描述..."></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="updateBookDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="updateBookAction('updateBook')">确定修改</el-button>
      </span>
    </el-dialog>

    <el-dialog title="添加笔记本" :visible.sync="addBookDialogVisible" :size="dialogSize">
      <el-form :model="createBook" ref="createBook" :rules="createRules">
        <el-form-item prop="bookName">
          <el-input v-model="createBook.bookName" placeholder="笔记本名称..."></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="createBook.bookDesc" type="textarea" placeholder="输入笔记本的描述..."></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addBookDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addBookAction('createBook')">确定添加</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script type="text/ecmascript-6">
  import BookList from './booklist/BookList.vue';
  import NoteList from './notelist/NoteList.vue';
  import Editor from './editor/Editor.vue';
  import { CommonService } from '../../service/CommonService';
  import { NoteBookService } from '../../service/NoteBookService';

  export default {
    created () {
      CommonService.isLogin(this.$store, () => {}, () => {
        this.$message.error('您还没有登录！');
        this.$router.push('login');
      });
      NoteBookService.findAll(data => {
        this.bookList = data;
        console.log(data);
      }, err => {
        this.$message.error(err);
      });
    },
    data () {
      return {
        createBook: {
          bookName: null,
          bookDesc: null
        },
        updateBook: {
          bookId: null,
          bookName: null,
          bookDesc: null
        },
        createRules: {
          bookName: [
            {require: true, message: '请输入笔记本名称', trigger: 'blur'},
            {min: 3, max: 10, message: '长度在3-10字符之间', trigger: 'blur'}
          ]
        },
        updateRules: {
          bookName: [
            {require: true, message: '请输入笔记本名称', trigger: 'blur'},
            {min: 3, max: 10, message: '长度在3-10字符之间', trigger: 'blur'}
          ]
        },
        bookList: [],
        showBookList: true,
        addBookDialogVisible: false,
        updateBookDialogVisible: false,
        openNote: [{
          title: '欢迎使用云笔记',
          name: 'welcome',
          content: '# 欢迎使用云笔记\n\n>这里使用的是`markdowm`编辑器。\n\n## 示例\n\n# h1\n## h2\n### h3\n\n```js\nvar a = \'Hello World!\';\nalert(a);\n```\n'
        }, {
          title: 'Tab 2',
          name: '2',
          content: 'Tab 2 content'
        }]
      };
    },
    components: {
      'v-book-list': BookList,
      'v-note-list': NoteList,
      'v-editor': Editor
    },
    methods: {
      addBookAction (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            NoteBookService.create(this.createBook, data => {
              this.bookList.push(data);
              this.$message.success('添加笔记本成功！');
              this.addBookDialogVisible = false;
              this.createBook.bookName = null;
              this.createBook.bookDesc = null;
            }, err => {
              this.$message.error(err);
            });
          } else {
            this.$message.error('请正确填写！');
            return false;
          }
        });
      },
      updateBookAction (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            NoteBookService.update(this.updateBook, data => {
              this.bookList = this.bookList.map(book => {
                if (book.cn_notebook_id === data.cn_notebook_id) return data;
                return book;
              });
              this.$message.success('修改笔记本成功！');
              this.updateBookDialogVisible = false;
              this.updateBook.bookName = null;
              this.updateBook.bookDesc = null;
            }, err => {
              this.$message.error(err);
            });
          } else {
            this.$message.error('请正确填写！');
            return false;
          }
        });
      },
      showAddBook () {
        this.addBookDialogVisible = true;
      },
      showUpdateBook (id) {
        var books = this.bookList.filter(book => {
          return book.cn_notebook_id === id;
        });
        this.updateBook.bookId = books[0].cn_notebook_id;
        this.updateBook.bookName = books[0].cn_notebook_name;
        this.updateBook.bookDesc = books[0].cn_notebook_desc;
        this.updateBookDialogVisible = true;
      },
      deleteBook (id) {
        NoteBookService.delete({bookId: id}, () => {
          this.$message.success('删除笔记本成功！');
          this.bookList = this.bookList.filter(book => {
            return book.cn_notebook_id !== id;
          });
          console.log(this.bookList);
        }, err => {
          this.$message.error(err);
        });
      },
      clickNote (note) {
        console.log(note);
        this.openNote.push({
          title: 'Tab 1' + new Date().getTime(),
          name: '3333' + new Date().getTime(),
          content: 'Tab 333 content'
        });
      }
    },
    computed: {
      dialogSize () {
        var winWidth = 768;
        if (window.innerWidth) winWidth = window.innerWidth;
        else if ((document.body) && (document.body.clientWidth)) winWidth = document.body.clientWidth;
        return winWidth < 768 ? 'full' : 'small';
      }
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
  .main
    position: relative
    > #book-list
    //z-index: 2000
      position: fixed
      overflow-x: hidden
      overflow-y: auto
      width: 200px
      top: 60px
      left: 0
      bottom: 0
      border-right: 1px solid rgba(0, 0, 0, .4);
      background-color: #eef1f6
    > .note-list
    //z-index: 1999
      position: fixed
      overflow: auto
      width: 300px
      top: 60px
      left: 200px
      bottom: 0
      border-right: 1px solid rgba(0, 0, 0, .4);
    .editor
    //z-index: 1998
      position: fixed
      overflow: auto
      top: 60px
      left: 500px
      right: 0
      bottom: 0
</style>
