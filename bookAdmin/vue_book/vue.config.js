const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  // 配置代理服务器
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:80",
        pathRewrite: { '^/api': '' }
      }
    }
  }
})
