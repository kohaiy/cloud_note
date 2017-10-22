<template>
  <div class="note-detail">
    <div class="header">
      <div class="title" v-text="note.cn_note_title"></div>
      <div class="id">(#<span v-text="note.cn_note_id"></span>)</div>
    </div>
    <div class="content" v-html="md2HTML(note.cn_note_content)"></div>
    <div class="time clearfix">
      <div class="pull-left">
        创建时间：<span v-text="formatDate(note.cn_note_createtime)"></span>
      </div>
      <div class="pull-right">
        最后编辑时间：<span v-text="formatDate(note.cn_note_updatetime)"></span>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import moment from 'moment';
  import marked from 'marked';

  moment.locale('zh-cn');

  marked.setOptions({
    highlight: function (code) {
      return require('highlight.js').highlightAuto(code).value;
    }
  });

  export default {
    props: {
      note: Object
    },
    methods: {
      formatDate (timestamp) {
        return moment(timestamp).fromNow();
      },
      md2HTML (md) {
        return marked(md);
      }
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
  .note-detail
    .header
      border-bottom: 1px dashed gray
      .title
        display: inline-block
        font-size: 25px
      .id
        display: inline-block
        font-size: 15px
    .content
      margin: 20px 0
      font-size: 20px
    .time
      padding: 10px 0
      border-top: 1px dashed gray
      border-bottom: 1px dashed gray
</style>
