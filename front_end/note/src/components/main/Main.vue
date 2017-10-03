<template>
  <div class="main">
    <transition name="book-list-fade">
      <div v-show="showBookList" id="book-list" class="book-list" ref="bookList">
        <v-book-list v-on:toggleBookList="toggleBookList"></v-book-list>
      </div>
    </transition>

    <div id="note-list" class="note-list">
      <v-note-list></v-note-list>
    </div>

    <div class="editor">
      <v-editor></v-editor>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import BookList from './booklist/BookList.vue';
  import NoteList from './notelist/NoteList.vue';
  import Editor from './editor/Editor.vue';
  import { CommonService } from '../../service/CommonService';

  export default {
    created () {
      CommonService.isLogin(this.$store, () => {}, () => {
        this.$message.error('您还没有登录！');
        this.$router.push('login');
      });
    },
    data () {
      return {
        showBookList: true
      };
    },
    components: {
      'v-book-list': BookList,
      'v-note-list': NoteList,
      'v-editor': Editor
    },
    methods: {
      toggleBookList () {
        this.$notify.error('功能暂未实现！');
//        this.showBookList = !this.showBookList;
//        var noteList = document.getElementById('note-list');
//        noteList.className = 'note-list-fade-enter-active,note-list-fade-enter';
      }
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
  .main
    position: relative
    .book-list-fade-enter-active, .note-list-fade-enter-active
      transition: all .3s ease
    .book-list-fade-leave-active, .note-list-fade-leave-active
      transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0)
    .book-list-fade-enter, .book-list-fade-leave-to
      left: -200px !important
    .note-list-fade-enter, .note-list-fade-leave-to
      left: 0 !important
    #toggle-books
      position: relative
    > #book-list
      position: fixed
      overflow-x: hidden
      overflow-y: auto
      width: 200px
      top: 60px
      left: 0
      bottom: 0
      background-color: #eef1f6
    > .note-list
      position: fixed
      overflow: auto
      width: 300px
      top: 60px
      left: 200px
      bottom: 0
      box-shadow: inset 10px 5px 25px -15px gray
    .editor
      position: fixed
      overflow: auto
      top: 60px
      left: 500px
      right: 0
      bottom: 0
</style>
