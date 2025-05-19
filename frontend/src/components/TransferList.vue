<template>
  <div class="table-wrapper">
    <h2>Extrato de Agendamentos</h2>
    <table class="table table-striped table-bordered table-hover mt-3">
      <thead>
        <tr>
          <th>Conta Origem</th>
          <th>Conta Destino</th>
          <th>Valor</th>
          <th>Taxa</th>
          <th>Data Agendamento</th>
          <th>Data Transferência</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="transfer in transferencias" :key="transfer.id">
          <td>{{ transfer.contaOrigem }}</td>
          <td>{{ transfer.contaDestino }}</td>
          <td>{{ formatarMoeda(transfer.valor) }}</td>
          <td>{{ formatarMoeda(transfer.taxa) }}</td>
          <td>{{ formatarData(transfer.dataAgendamento) }}</td>
          <td>{{ formatarData(transfer.dataTransferencia) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const transferencias = ref([]);

onMounted(async () => {
  const response = await fetch('http://localhost:8080/transferencias');
  transferencias.value = await response.json();
});

// Formatador de moeda BRL
const formatarMoeda = (valor) => {
  if (valor == null) return 'R$ 0,00';
  return Number(valor).toLocaleString('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  });
};

// Formatador de data dd/MM/yyyy
const formatarData = (dataISO) => {
  if (!dataISO) return '';
  const [ano, mes, dia] = dataISO.split('-');
  return `${dia}/${mes}/${ano}`;
};
</script>
<style scoped>
.table-wrapper {
  overflow-x: auto; /* permite scroll horizontal */
  background-color: #f8f9fa; /* fundo leve, como no Bootstrap */
  padding: 1rem;
  border-radius: 6px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
  table-layout: fixed; /* largura fixa para colunas */
  word-wrap: break-word; /* quebra palavras grandes */
}

th, td {
  padding: 8px;
  text-align: center;
  border: 1px solid #ccc;
  max-width: 120px; /* limita largura máxima da célula */
  white-space: nowrap; /* evita quebra de linha dentro da célula */
  overflow: hidden;
  text-overflow: ellipsis; /* mostra '...' para textos longos */
}

th {
  background-color: #f4f4f4;
}
</style>

