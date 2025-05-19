<script setup>
import { reactive, ref, watch, onMounted } from 'vue';

const form = reactive({
  contaOrigem: '',
  contaDestino: '',
  valor: 0,
  dataTransferencia: ''
});

const formattedValor = ref('');
const erro = ref('');
const dataInvalida = ref(false);

const formatarParaReal = (valor) => {
  if (valor === null || valor === undefined || valor === '') return '';
  return Number(valor).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
};

const parseMoeda = (valorFormatado) => {
  if (!valorFormatado) return 0;
  // Remove tudo exceto dígitos e vírgulas, troca vírgula por ponto
  const numeroStr = valorFormatado.replace(/[^\d,]/g, '').replace(',', '.');
  const numero = parseFloat(numeroStr);
  return isNaN(numero) ? 0 : numero;
};

const onValorInput = (event) => {
  const valorDigitado = event.target.value;
  const valorNumerico = parseMoeda(valorDigitado);
  form.valor = valorNumerico;
  formattedValor.value = formatarParaReal(valorNumerico);
};

watch(() => form.valor, (novoValor) => {
  formattedValor.value = formatarParaReal(novoValor);
});

const somenteNumeros = (campo) => {
  form[campo] = form[campo].replace(/\D/g, '').slice(0, 10);
};

const validarData = () => {
  const hoje = new Date().toISOString().split('T')[0];
  dataInvalida.value = form.dataTransferencia < hoje;

  if (dataInvalida.value) {
    erro.value = 'A data da transferência não pode ser anterior a hoje.';
  } else {
    erro.value = '';
  }
};

const agendar = async () => {
  validarData();
  if (erro.value) return;

  form.contaOrigem = form.contaOrigem.trim();
  form.contaDestino = form.contaDestino.trim();

  try {
    const response = await fetch('http://localhost:8080/transferencias', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form)
    });

    if (!response.ok) {
      const msg = await response.text();
      throw new Error(msg);
    }

    alert('Transferência agendada com sucesso!');
    erro.value = '';
  } catch (e) {
    erro.value = e.message;
  }
};

onMounted(() => {
  formattedValor.value = formatarParaReal(form.valor);
});
</script>

<template>
  <div>
    <form @submit.prevent="agendar">
      <input
        v-model="form.contaOrigem"
        placeholder="Conta de Origem"
        maxlength="10"
        @input="somenteNumeros('contaOrigem')"
        required
      />
      <input
        v-model="form.contaDestino"
        placeholder="Conta de Destino"
        maxlength="10"
        @input="somenteNumeros('contaDestino')"
        required
      />

      <input
        v-model="formattedValor"
        @input="onValorInput"
        placeholder="Valor"
        type="text"
        required
      />

      <input
        v-model="form.dataTransferencia"
        type="date"
        :class="{ invalido: dataInvalida }"
        @change="validarData"
        required
      />

      <button
        type="submit"
        :disabled="erro || dataInvalida || !form.contaOrigem || !form.contaDestino || !form.valor || !form.dataTransferencia"
      >
        Agendar
      </button>
    </form>

    <p v-if="erro" class="erro">{{ erro }}</p>
  </div>
</template>

<style scoped>
.invalido {
  border: 2px solid red;
}
.erro {
  color: red;
  margin-top: 10px;
}
/* Centraliza o container todo na tela */
div {
  display: flex;
  justify-content: center; /* centraliza horizontalmente */
  padding: 20px;
}

/* Form empilhado e centralizado */
form {
  display: flex;
  flex-direction: column;
  align-items: center; /* centraliza inputs */
  width: 300px; /* largura fixa para o form */
  background: #f9f9f9;
  padding: 20px;
  border-radius: 6px;
  box-shadow: 0 0 10px rgba(0,0,0,0.1);
}

/* Inputs empilhados com espaçamento */
input {
  display: block;
  width: 100%;
  margin-bottom: 12px;
  padding: 8px;
  font-size: 16px;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #ccc;
}

/* Botão centralizado e com estilo */
button {
  width: 100%;
  padding: 10px;
  font-size: 18px;
  border-radius: 4px;
  border: none;
  background-color: #007BFF;
  color: white;
  cursor: pointer;
}

button:disabled {
  background-color: #aaa;
  cursor: not-allowed;
}

</style>
