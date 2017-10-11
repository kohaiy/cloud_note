<template>
  <div class="note">
    <div class="icon"><i class="el-icon-document"></i></div>
    <div class="title" v-text="note.title"></div>
    <div class="datetime">
      最后编辑：<span v-text="note.datetime"></span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    props: ['note']
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
  .note
    position: relative
    height: 50px
    border-bottom: 1px solid rgba(0, 0, 0, .1)
    cursor: pointer
    transition: all .3s
    color: #324157
    .icon
      width: 40px
      height: 50px
      line-height: 50px
      float: left
      margin-left: 10px
      font-size: 30px
    .title
      height: 30px
      padding-top: 10px
      overflow: hidden
      text-overflow: ellipsis
      word-break: keep-all
      font-size: 18px
    .datetime
      position: absolute
      right: 5px
      bottom: 0
      font-size: 12px
      color: #AEAEAE
    &:hover
      background-color: rgba(0, 0, 0, .1)
</style>
