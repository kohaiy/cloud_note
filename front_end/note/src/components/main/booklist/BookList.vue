<template>
  <div class="book-list">
    <div class="header">
      <div class="title">我的笔记本</div>
      <div id="add-book" @click="addBook">
        <i class="el-icon-plus"></i>
      </div>
    </div>
    <el-popover
      ref="popover1"
      placement="top-start"
      title="标题"
      width="200"
      trigger="click"
      content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">
    </el-popover>
    <el-menu id="menu" default-active="1">
      <el-submenu index="1">
        <template slot="title"><i class="text-icon glyphicon glyphicon-pencil"></i>我的分类</template>
        <div v-if="bookList == null || bookList.length == 0" style="text-align:center;height: 50px;line-height: 50px;">
          暂没有笔记本！
        </div>
        <el-menu-item v-for="(item,index) in bookList" :index="index" :title="item.cn_notebook_desc">
          <el-popover
            placement="top"
            title="操作"
            trigger="hover">
            <div class="text-center">
              <el-button @click="updateBook(item.cn_notebook_id)" type="primary" icon="edit" size="mini">修改</el-button>
              <el-button @click="deleteBook(item.cn_notebook_id)" type="danger" icon="delete" size="mini">删除</el-button>
            </div>
            <span class="el-icon-date" slot="reference"></span>
          </el-popover>
          {{item.cn_notebook_name | bookName}}
        </el-menu-item>
      </el-submenu>
      <el-menu-item index="2"><i class="text-icon glyphicon glyphicon-heart"></i>收藏</el-menu-item>
      <el-menu-item index="3"><i class="text-icon glyphicon glyphicon-star"></i>活动</el-menu-item>
      <el-menu-item index="4"><i class="text-icon glyphicon glyphicon-trash"></i>回收站</el-menu-item>
    </el-menu>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    created () {
    },
    props: {
      bookList: Array
    },
    data () {
      return {};
    },
    methods: {
      addBook () {
        this.$emit('addBook');
      },
      updateBook (id) {
        this.$emit('updateBook', id);
      },
      deleteBook (id) {
        this.$confirm('此操作将永久删除该笔记本及其笔记, 是否继续?', '提示', {
          confirmButtonText: '我确定我没疯！',
          cancelButtonText: '容我三思~',
          type: 'error'
        }).then(() => {
          this.$emit('deleteBook', id);
        }).catch();
      }
    },
    computed: {},
    filters: {
      bookName: function (value) {
        if (value == null) return '未命名';
        return value;
      }
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
  .book-list
    position: relative
    .header
      .title
        padding: 5px 0
        text-align: center
        font-size: 18px
        color: white
        background-color: #20A0FF
      #add-book
        height: 35px
        position: absolute
        right: 5px
        top: 0
        line-height: 35px
        font-size: 20px
        color: rgba(255, 255, 255, .5)
        cursor: pointer
        transition: all .3s
        &:hover
          color: white;
</style>
