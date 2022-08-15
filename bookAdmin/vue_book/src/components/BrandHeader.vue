<template>
  <div>
    <el-form :inline="true" :model="brand" class="demo-form-inline">
      <el-form-item label="当前状态">
        <el-select v-model="brand.status" placeholder="当前状态">
          <el-option label="启用" value="1"></el-option>
          <el-option label="禁用" value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="企业名称">
        <el-input v-model="brand.brandName" placeholder="企业名称"></el-input>
      </el-form-item>
      <el-form-item label="品牌名称">
        <el-input v-model="brand.companyName" placeholder="品牌名称"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "BrandName",
  data() {
    return {
      brand: {
        brandName: "",
        companyName: "",
        status: "",
      },
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!", this.brand);
    },
  },
};
</script>

<style>
</style>