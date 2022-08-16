<template>
  <div>
    <el-row>
      <el-button type="danger" plain>批量删除</el-button>
      <el-button type="primary" @click="dialogVisible = true" plain
        >新增品牌</el-button
      >
      <!-- 弹窗 -->
      <el-dialog
        title="新增品牌"
        :visible.sync="dialogVisible"
        width="40%"
        :before-close="handleClose"
      >
        <!-- 新增表单 -->
        <el-form ref="form" :model="form" :rules="rules" label-width="100px">
          <el-form-item prop="brandName" label="品牌名称">
            <el-input v-model="form.brandName"></el-input>
          </el-form-item>
          <el-form-item prop="companyName" label="企业名称">
            <el-input v-model="form.companyName"></el-input>
          </el-form-item>
          <el-form-item prop="ordered" label="排序(数字)">
            <el-input v-model.number="form.ordered"></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input type="textarea" v-model="form.description"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-switch
              v-model="form.status"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-value="1"
              inactive-value="0"
            >
            </el-switch>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm('form')" :plain="true"
              >添加</el-button
            >
            <el-button @click="dialogVisible = false">取 消</el-button>
          </el-form-item>
        </el-form>
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
        <el-button type="primary" @click="dialogVisible = true">修改</el-button>

        <el-button type="danger">删除</el-button>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="40"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { selectAll, addBrand } from "@/api";
export default {
  name: "Brand",

  mounted() {
    this.getAll();
  },

  data() {
    return {
      //   addStatus: "",
      dialogVisible: false,
      form: {
        brandName: "",
        companyName: "",
        ordered: "",
        description: "",
        status: "1",
      },
      rules: {
        brandName: [
          { required: true, message: "请输入品牌名称", trigger: "blur" },
          {
            min: 3,
            max: 15,
            message: "长度在 3 到 15 个字符",
            trigger: "blur",
          },
        ],
        companyName: [
          { required: true, message: "请输入企业名称", trigger: "blur" },
        ],
        ordered: [
          {
            required: true,
            message: "请填写排序字段(越大排名越靠前)",
            trigger: "blur",
          },
          { type: "number", message: "排序字段必须为数字值" },
        ],
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
      currentPage1: 5,
      currentPage2: 5,
      currentPage3: 5,
      currentPage4: 1,
    };
  },
  methods: {
    async getAll() {
      const brands = await selectAll();
      this.tableData = brands;
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          // 点击右上角的叉叉后要清空表单后再关闭，取消按钮不需要清空内容
          this.form.brandName = "";
          this.form.companyName = "";
          this.form.ordered = "";
          this.form.description = "";
          done();
        })
        .catch((_) => {});
    },
    // 复选框选中的方法
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
    },
    // 隔行变色
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 === 1) {
        return "";
      } else if (rowIndex % 2 === 0) {
        return "success-row";
      }
    },
    // 新增品牌表单提交
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          //   发送ajax请求
          const data = await addBrand(this.form);
          if (data == "success") {
            this.dialogVisible = false;
            this.$message({
              message: "恭喜你，添加成功",
              type: "success",
            });
          } else {
            this.dialogVisible = true;
            this.$message.error(`添加失败`);
          }
        } else {
          this.$message.error("错了哦，请检查表单");
          return false;
        }
        this.getAll();
      });
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
  },
};
</script>

<style>
.el-table .success-row {
  background: #f0f9eb;
}
</style>