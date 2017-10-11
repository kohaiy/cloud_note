<template>
  <div class="editor">
    <el-tabs v-model="editableTabsValue" closable>
      <el-tab-pane
        v-for="(item, index) in editableTabs2"
        :key="item.name"
        :label="item.title"
        :name="item.name"
      >
        <div class="title-label" v-text="item.title"></div>
        <div id="editor">
          <el-input v-model="item.title" id="note-title" placeholder="笔记标题..."></el-input>
          <div class="pull-right">
            预览
            <el-switch
              v-model="showPreview" on-text="" off-text="">
            </el-switch>
          </div>
          <el-input v-model="item.content" id="note-content" autosize type="textarea" placeholder="开始记录..."></el-input>
          <transition name="el-fade-in-linear">
            <div v-show="showPreview" id="preview" v-html="markedTools(item.content)"></div>
          </transition>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script type="text/ecmascript-6">
  import marked from 'marked';

  // Synchronous highlighting with highlight.js
  marked.setOptions({
    highlight: function (code) {
      return require('highlight.js').highlightAuto(code).value;
    }
  });

  export default {
    props: {
      editableTabs2: Array
    },
    created () {
//      var data = [{
//        title: 'Tab 1',
//        name: '1',
//        content: 'Tab 1 content'
//      }, {
//        title: 'Tab 2',
//        name: '2',
//        content: 'Tab 2 content'
//      }];
//      this.editableTabs2.push(data[0]);
//      this.editableTabs2.push(data[1]);
    },
    data () {
      return {
        showPreview: false,
        editableTabsValue: 'welcome',
//        editableTabs2: [],
        tabIndex: 2
      };
    },
    methods: {
      markedTools (md) {
        return marked(md);
      }
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
  @import "../../../../node_modules/highlight.js/styles/github.css"
  .editor
    background-color: rgba(50, 65, 87, .1)
    box-shadow: inset 10px 5px 25px -15px gray
    #editor
      position: relative
      padding: 0 10px
      margin-bottom: 50px
      #note-title
        margin: 10px 0
      #preview
        position: relative
        overflow: auto
        width: 100%
        min-height: 50px
        margin: 10px 0
        padding: 5px
        background-color: #fff
        &:after
          position: absolute
          top: 5px
          right: 10px
          content: '预览'
          color: rgba(32, 160, 255, .5)
        table
          border: 1px solid gray
</style>
