<template>
  <div>
    <el-row>
      <el-button type="danger" plain>批量删除</el-button>
      <el-button type="primary" @click="dialogVisible = true" plain
        >新增品牌</el-button
      >
      <!-- 弹窗 -->
      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
      >
        <!-- 新增表单 -->
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item prop="name" label="品牌名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item prop="name" label="活动区域">
            <el-select v-model="form.region" placeholder="请选择活动区域">
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="活动形式">
            <el-input type="textarea" v-model="form.desc"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-switch v-model="form.delivery"></el-switch>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="onSubmit">确 定</el-button>
        </span>
      </el-dialog>
    </el-row>
    <el-table
      :data="tableData"
      style="width: 100%"
      :row-class-name="tableRowClassName"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column align="center" type="index" width="50">
      </el-table-column>
      <el-table-column align="center" prop="brandName" label="品牌名称">
      </el-table-column>
      <el-table-column align="center" prop="companyName" label="企业名称">
      </el-table-column>
      <el-table-column align="center" prop="ordered" label="排序">
      </el-table-column>
      <el-table-column align="center" prop="status" label="当前状态">
      </el-table-column>
      <el-table-column align="center" label="操作">
        <el-button type="primary">修改</el-button>
        <el-button type="danger">删除</el-button>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "Brand",

  data() {
    return {
      dialogVisible: false,
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入活动名称", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        region: [
          { required: true, message: "请选择活动区域", trigger: "change" },
        ],
        date1: [
          {
            type: "date",
            required: true,
            message: "请选择日期",
            trigger: "change",
          },
        ],
        date2: [
          {
            type: "date",
            required: true,
            message: "请选择时间",
            trigger: "change",
          },
        ],
        type: [
          {
            type: "array",
            required: true,
            message: "请至少选择一个活动性质",
            trigger: "change",
          },
        ],
        resource: [
          { required: true, message: "请选择活动资源", trigger: "change" },
        ],
        desc: [{ required: true, message: "请填写活动形式", trigger: "blur" }],
      },
      //   多选框
      multipleSelection: [],
      tableData: [
        {
          brandName: "华为",
          companyName: "华为科技有限公司",
          ordered: "100",
          status: "1",
        },
        {
          brandName: "华为",
          companyName: "华为科技有限公司",
          ordered: "100",
          status: "1",
        },
        {
          brandName: "华为",
          companyName: "华为科技有限公司",
          ordered: "100",
          status: "1",
        },
        {
          brandName: "华为",
          companyName: "华为科技有限公司",
          ordered: "100",
          status: "1",
        },
      ],
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    // 复选框选中的方法
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return "warning-row";
      } else if (rowIndex === 3) {
        return "success-row";
      }
      return "";
    },
    onSubmit() {
      console.log("submit!");
      this.dialogVisible = false;
    },
  },
};
</script>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>