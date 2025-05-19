<template>
  <div class="container mt-12">
    <h1 class="mb-4 text-center">Extrato de Transferências</h1>
    <TransferList />
  </div>
</template>

<script>
import TransferList from '@/components/TransferList.vue';

export default {
  name: 'ExtratoView',
  components: {
    TransferList
  }
};
</script>
