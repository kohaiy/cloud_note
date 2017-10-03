<template>
  <div class="book-list">
    <div class="header">
      <div class="title">我的笔记本</div>
      <div id="toggle" @click="toggleBookList">
        <i class="el-icon-caret-left"></i>
      </div>
    </div>
    <el-menu id="menu" default-active="1">
      <el-submenu index="1">
        <template slot="title"><i class="text-icon glyphicon glyphicon-pencil"></i>我的分类</template>
        <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="1-1">选项1</el-menu-item>
          <el-menu-item index="1-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="1-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="1-4">
          <template slot="title">选项4</template>
          <el-menu-item index="1-4-1">选项1</el-menu-item>
          <el-menu-item index="1-4-2">选项2</el-menu-item>
          <el-menu-item index="1-4-3">选项3</el-menu-item>
          <el-menu-item index="1-4-4">选项4</el-menu-item>
          <el-menu-item index="1-4-5">选项5</el-menu-item>
          <el-menu-item index="1-4-6">选项6</el-menu-item>
          <el-menu-item index="1-4-7">选项7</el-menu-item>
        </el-submenu>
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
    methods: {
      toggleBookList () {
        this.$emit('toggleBookList');
      }
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
  .book-list
    position: relative
    .header
      position: absolute
      z-index: 100
      top: 0
      left: 0
      right: 0
      .title
        padding: 5px 0
        text-align: center
        font-size: 18px
        color: white
        background-color: #20A0FF
      #toggle
        height: 35px
        position: absolute
        right: 0
        top: 0
        line-height: 35px
        font-size: 25px
        color: rgba(255, 255, 255, .5)
        cursor: pointer
        transition: all .3s
        &:hover
          color: white;
    #menu
      padding-top: 35px
</style>
