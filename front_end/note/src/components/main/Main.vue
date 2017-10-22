<template>
  <div class="main">

    <input id="show-book-check" type="checkbox">
    <label id="show-book-btn" for="show-book-check"><i class="el-icon-arrow-right"></i><i
      class="el-icon-arrow-left"></i></label>

    <div id="book-list" class="book-list">
      <v-book-list
        v-on:addBook="showAddBook"
        v-on:updateBook="showUpdateBook"
        v-on:deleteBook="deleteBook"
        v-on:selectBook="changeBook"
        :bookList="bookList"></v-book-list>
    </div>

    <div id="note-list" class="note-list">
      <v-note-list
        v-on:addNote="addNote"
        v-on:clickNote="clickNote"
        :noteList="selectedNoteList"
      ></v-note-list>
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

    <el-dialog :title="selectedBook ? '添加笔记 -> 《'+selectedBook.cn_notebook_name+'》' : ''"
               :visible.sync="addNoteDialogVisible" size="full">
      <el-form :model="createNoteForm" ref="createNoteForm">
        <el-form-item>
          <el-input v-model="createNoteForm.cn_note_title" placeholder="笔记名称..."></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="createNoteForm.cn_note_content" autosize type="textarea" placeholder="开始记录..."></el-input>
        </el-form-item>
        <div class="md-preview" v-html="md2HTML(createNoteForm.cn_note_content)"></div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addNoteDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addNoteAction('createNoteForm')">确定添加</el-button>
      </span>
    </el-dialog>

    <el-dialog title="查看笔记" :visible.sync="showNoteDialogVisible" size="full">
      <v-note-detail
        :note="selectedNote"
      ></v-note-detail>
    </el-dialog>

  </div>
</template>

<script type="text/ecmascript-6">
  import marked from 'marked';
  import BookList from './booklist/BookList.vue';
  import NoteList from './notelist/NoteList.vue';
  import Editor from './editor/Editor.vue';
  import NoteDetail from './note_detail/NoteDetail.vue';
  import { CommonService } from '../../service/CommonService';
  import { NoteBookService } from '../../service/NoteBookService';
  import { NoteService } from '../../service/NoteService';

  marked.setOptions({
    highlight: function (code) {
      return require('highlight.js').highlightAuto(code).value;
    }
  });

  export default {
    created () {
      CommonService.isLogin(this.$store, () => {}, () => {
        this.$message.error('您还没有登录！');
        this.$router.push('login');
      });
      NoteBookService.findAll(data => {
        this.bookList = data;
        console.log('----NoteBookList----');
        console.log(data);
        console.log('----/NoteBookList----');
      }, err => {
        this.$message.error(err);
      });
      NoteService.findAll(data => {
        this.noteList = data;
        console.log('----NoteList----');
        console.log(data);
        console.log('----/NoteList----');
      }, err => {
        this.$message.error(err);
      });
    },
    data () {
      return {
        selectedBook: null,
        selectedNote: null,
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
        createNoteForm: {
          cn_note_title: '',
          cn_note_content: ''
        },
        bookList: [],
        noteList: [],
        selectedNoteList: [],
        showBookList: true,
        addBookDialogVisible: false,
        updateBookDialogVisible: false,
        addNoteDialogVisible: false,
        showNoteDialogVisible: false,
        openNote: [{
          title: '欢迎使用云笔记',
          name: 'welcome',
          content: '# 欢迎使用云笔记\n\n>这里使用的是`markdowm`编辑器。\n\n## 示例\n\n# h1\n## h2\n### h3\n\n```js\nvar a = \'Hello World!\';\nalert(a);\n```\n'
        }],
        noteTabsValue: 'welcome'
      };
    },
    components: {
      'v-book-list': BookList,
      'v-note-list': NoteList,
      'v-editor': Editor,
      'v-note-detail': NoteDetail
    },
    methods: {
      changeBook (id) {
        this.selectedBook = this.bookList.filter(book => {
          return book.cn_notebook_id === +id;
        })[0];
        this.selectedNoteList = this.noteList.filter(note => {
          return note.cn_notebook_id === +id;
        });
//        this.$message.success(id);
      },
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
//        this.$message.success(note.toString());
        this.selectedNote = note;
        this.showNoteDialogVisible = true;
//        var flag = this.openNote.some(_note => {
//          return _note.name === note.cn_note_id;
//        });
//        if (!flag) {
//          this.openNote.push({
//            title: note.cn_note_title,
//            name: note.cn_note_id,
//            content: note.cn_note_content
//          });
//        }
//        this.noteTabsValue = note.cn_note_id.toString();
      },
      addNote () {
        if (!this.selectedBook) {
          this.$message.error('请先选择一个笔记本！');
        } else {
          this.addNoteDialogVisible = true;
        }
//        var note = {
//          title: '新建笔记',
//          name: 'new' + new Date().getTime(),
//          content: ''
//        };
//        this.openNote.push(note);
//        this.noteTabsValue = note.name;
      },
      addNoteAction () {
        let postData = {
          bookId: this.selectedBook.cn_notebook_id,
          title: this.createNoteForm.cn_note_title,
          content: this.createNoteForm.cn_note_content
        };
        NoteService.create(postData, data => {
          this.$message.success('添加笔记成功！');
          this.noteList.push(data);
          this.addNoteDialogVisible = false;
          this.createNoteForm = {
            cn_note_title: '',
            cn_note_content: ''
          };
          this.selectedNoteList = this.noteList.filter(note => {
            return note.cn_notebook_id === this.selectedBook.cn_notebook_id;
          });
        }, err => {
          this.$message.error(err);
        });
      },
      closeTab (name) {
        this.openNote = this.openNote.filter(note => {
          return note.name.toString() !== name.toString();
        });
        this.noteTabsValue = this.openNote[0] === undefined ? '' : this.openNote[0].name + '';
        console.log(typeof this.noteTabsValue);
      },
      noteTabsValueChange (val) {
        this.noteTabsValue = val;
      },
      md2HTML (md) {
        return marked(md);
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

<style lang="stylus" ref="stylesheet/stylus">
  @import "../../../node_modules/highlight.js/styles/github.css"
  @media screen and (max-width: 768px)
    .main
      > #book-list
        z-index: 1002
        left: -200px !important
        transition: all .3s
      > #note-list
        z-index: 1000
        left: 0px
        transition: all .3s
      > #show-book-btn
        z-index: 1001
        position: fixed
        width: 20px
        height: 100%
        top: 60px
        bottom: 0
        left: 0
        cursor: pointer
        transition: all .3s
        &:active
          background-color: rgba(0, 0, 0, .1)
        > .el-icon-arrow-right
        > .el-icon-arrow-left
          position: absolute
          top: 50%
          font-size: 20px
        > .el-icon-arrow-left
          display: none
      > #show-book-check:checked
        & ~ #book-list
          left: 0 !important
        & ~ #show-book-btn
          width: 100%
          height: 100%
          left: 0
          right: 0
          bottom: 0
          background-color: rgba(0, 0, 0, .5)
          > .el-icon-arrow-right
          > .el-icon-arrow-left
            display: none

  .main
    position: relative
    > #book-list
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
      position: fixed
      overflow: auto
      top: 60px
      left: 200px
      right: 0
      bottom: 0
    .md-preview
      position: relative
      min-height: 50px
      padding: 5px
      border: 1px solid #bfcbd9
      border-radius: 4px
      &:before
        content: '预览'
        position: absolute
        top: 5px
        right: 10px
        color: #bfcbd9
      & > table
        border: 1px solid gray
</style>
