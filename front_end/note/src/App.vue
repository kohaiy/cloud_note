<template>
  <div id="app">
    <v-header></v-header>
    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import Header from './components/header/Header.vue';

  export default {
    name: 'app',
    methods: {},
    components: {
      'v-header': Header
    }
  };
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
  #app
    position: relative
    .content
      margin-top: 60px
</style>
