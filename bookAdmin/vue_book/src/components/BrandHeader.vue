<template>
  <div>
    <el-row>
      <el-form :inline="true" :model="brand" class="demo-form-inline">
        <el-col :span="6">
          <el-form-item label="当前状态">
            <el-select v-model="brand.status" placeholder="当前状态">
              <el-option label="启用" value="1"></el-option>
              <el-option label="禁用" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="企业名称">
            <el-input
              v-model="brand.brandName"
              placeholder="企业名称"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="品牌名称">
            <el-input
              v-model="brand.companyName"
              placeholder="品牌名称"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <div class="block" style="margin-left: 188px">
            <el-avatar :size="50" :src="circleUrl"></el-avatar>
          </div>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "BrandName",
  data() {
    return {
      brand: {
        id: "",
        brandName: "",
        companyName: "",
        status: "",
        description: "",
        ordered: "",
      },
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
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