<template>
  <div class="note-list">
    <div class="header">
      <div class="title">笔记列表</div>
    </div>
    <div class="content">
      <el-row>
        <div v-for="note in noteList" :key="note.id" @click="clickNote(note)">
          <v-note :note="note"></v-note>
        </div>
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
    },
    methods: {
      clickNote (note) {
        this.$emit('clickNote', note);
      }
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
    .content
      .note-item
        box-sizing: border-box
        padding: 10px
</style>
