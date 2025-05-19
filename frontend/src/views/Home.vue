<template>
  <div class="container mt-5">
    <div class="card shadow">
      <div class="card-body">
        <h1 class="card-title mb-4 text-center">Agendar Transferência</h1>
        <TransferForm />
      </div>
    </div>
  </div>
</template>

<script>
import TransferForm from '@/components/TransferForm.vue';

export default {
  name: 'HomeView',
  components: {
    TransferForm
  }
};
</script>
