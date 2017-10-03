<template>
  <div class="note-list">
    <div class="header">
      <div class="title">笔记列表</div>
    </div>
    <div class="content">
      <el-row>
        <v-note v-for="note in noteList" :note="note" :key="note.id"></v-note>
      </el-row>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import Note from './note/Note.vue';

  export default {
    data () {
      return {
        noteList: [
          {
            title: 'AAA',
            content: 'AAA CONTENT',
            datetime: '2014-23-32'
          },
          {
            title: 'BBB',
            content: 'BBB CONTENT',
            datetime: '2014-12-32'
          },
          {
            title: 'BBB',
            content: 'BBB CONTENT',
            datetime: '2014-12-32'
          },
          {
            title: 'BBBDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',
            content: 'BBB CONTENT',
            datetime: '2014-12-32 23:21:23'
          },
          {
            title: 'BBB',
            content: 'BBB CONTENT',
            datetime: '2014-12-32'
          },
          {
            title: 'BBBDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',
            content: 'BBB CONTENT',
            datetime: '2014-12-32 23:21:23'
          },
          {
            title: 'BBB',
            content: 'BBB CONTENT',
            datetime: '2014-12-32'
          },
          {
            title: 'BBBDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',
            content: 'BBB CONTENT',
            datetime: '2014-12-32 23:21:23'
          },
          {
            title: 'BBB',
            content: 'BBB CONTENT',
            datetime: '2014-12-32'
          },
          {
            title: 'BBBDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',
            content: 'BBB CONTENT',
            datetime: '2014-12-32 23:21:23'
          },
          {
            title: 'BBB',
            content: 'BBB CONTENT',
            datetime: '2014-12-32'
          },
          {
            title: 'BBBDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',
            content: 'BBB CONTENT',
            datetime: '2014-12-32 23:21:23'
          },
          {
            title: 'BBB',
            content: 'BBB CONTENT',
            datetime: '2014-12-32'
          },
          {
            title: 'BBBDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',
            content: 'BBB CONTENT',
            datetime: '2014-12-32 23:21:23'
          }
        ]
      };
    },
    components: {
      'v-note': Note
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
  .note-list
    .header
      .title
        padding: 5px 0
        text-align: center
        font-size: 18px
        color: white
        background-color: #20A0FF
        box-shadow: inset 10px 5px 25px -15px gray
    .content
      .note-item
        box-sizing: border-box
        padding: 10px
</style>
