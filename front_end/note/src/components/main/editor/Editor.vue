<template>
  <div class="editor">
    <h1 v-if="noteTabs == null || noteTabs.length == 0">请从左侧打开或新建笔记！</h1>
    <el-tabs v-model="noteTabsValue2"
             @tab-remove="closeNote"
             closable>
      <el-tab-pane
        v-for="(item, index) in noteTabs"
        :key="item.name.toString()"
        :label="item.title"
        :name="item.name.toString()"
      >
        <h2 class="title-label text-center" v-text="item.title"></h2>
        <div id="content" v-html="markedTools(item.content)"></div>
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
      noteTabs: Array,
      noteTabsValue: String
    },
    created () {},
    data () {
      return {
        showPreview: false,
        noteTabsValue2: this.noteTabsValue.toString()
      };
    },
    methods: {
      markedTools (md) {
        return marked(md);
      },
      closeNote (name) {
        this.$emit('closeTab', name);
      }
    },
    watch: {
      noteTabsValue (val) {
        this.noteTabsValue2 = val.toString();
      },
      noteTabsValue2 (val) {
        this.$emit('noteTabsValueChange', val);
      }
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
  @import "../../../../node_modules/highlight.js/styles/github.css"
  .editor
    background-color: rgba(50, 65, 87, .1)
    box-shadow: inset 10px 5px 25px -15px gray
    #content
      position: relative
      overflow: auto
      width: 100%
      min-height: 50px
      margin: 0 10px
      padding: 5px
      background-color: #fff
      table
        border: 1px solid gray
</style>
