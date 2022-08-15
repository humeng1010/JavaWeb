<template>
  <div>
    <BrandHeader></BrandHeader>
    <Brand></Brand>
  </div>
</template>

<script>
import BrandHeader from "@/components/BrandHeader.vue";
import Brand from "@/components/Brand.vue";
export default {
  name: "App",
  components: {
    Brand,
    BrandHeader,
  },
};
</script>

<style>
</style>
