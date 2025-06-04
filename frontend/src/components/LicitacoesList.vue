<script setup>
import axios from "axios";
import { onMounted, ref, watch } from "vue";
import { ChevronUp, ChevronDown } from "lucide-vue-next";

const licitacoesData = ref([]);
const activeAccordion = ref(null);
const currentPage = ref(0);
const totalPages = ref(0);
const pageSize = 5;
const notFound = ref(false);

const props = defineProps({
  filters: {
    type: Object,
    default: () => ({}),
  },
});

const fetchLicitacoes = async (page = 0) => {
  try {
    let url = `http://localhost:8080/licitacoes?page=${page}&size=${pageSize}`;

    if (props.filters.pregao) {
      url = `http://localhost:8080/licitacoes/pregao?numeroPregao=${props.filters.pregao}`;
    } else if (props.filters.uasg) {
      url = `http://localhost:8080/licitacoes/uasg/${props.filters.uasg}`;
    }

    const response = await axios.get(url);
    licitacoesData.value = response.data.content;
    totalPages.value = response.data.totalPages;
    notFound.value = licitacoesData.value.length === 0;
  } catch (error) {
    if (error.response && error.response.status === 404) {
      notFound.value = true;
    } else {
      console.error("Erro ao buscar licitações:", error);
    }
  }
};

watch(
  () => props.filters,
  () => {
    fetchLicitacoes();
  }
);

const toggleAccordion = (id) => {
  activeAccordion.value = activeAccordion.value === id ? null : id;
};

const goToPreviousPage = () => {
  if (currentPage.value > 0) {
    currentPage.value--;
    fetchLicitacoes(currentPage.value);
  }
};

const goToNextPage = () => {
  if (currentPage.value < totalPages.value - 1) {
    currentPage.value++;
    fetchLicitacoes(currentPage.value);
  }
};

onMounted(() => {
  fetchLicitacoes();
});
</script>

<template>
  <div class="space-y-4">
    <div v-if="notFound" class="text-center text-gray-500">
      <p class="text-lg font-medium">Nenhuma licitação encontrada.</p>
      <p class="text-sm">Verifique os filtros aplicados e tente novamente.</p>
      <button
        class="mt-4 px-4 py-2 bg-green-700 text-white rounded hover:bg-green-800"
        @click="fetchLicitacoes()"
      >
        Voltar para a lista
      </button>
    </div>

    <div v-else>
      <div
        v-for="licitacao in licitacoesData"
        :key="licitacao.id"
        class="border-l-4 border-l-green-700 bg-white border border-gray-300 rounded-lg"
      >
        <div class="p-0">
          <div class="w-full">
            <div class="border-0">
              <!-- "Header" -->
              <div
                class="px-6 py-4 hover:no-underline cursor-pointer flex items-center justify-between"
                @click="toggleAccordion(licitacao.id)"
              >
                <div class="flex flex-col items-start text-left gap-1">
                  <div class="flex items-center gap-2">
                    <span class="bg-green-700 text-white px-2 py-1 rounded">
                      {{ licitacao.id }}
                    </span>
                    <h3 class="font-bold">
                      Pregão Nº {{ licitacao.numeroPregao }}
                    </h3>
                  </div>
                  <div class="text-sm text-gray-600">
                    <span class="font-medium">
                      Código da UASG: {{ licitacao.codigoUasg }}
                    </span>
                  </div>
                  <div class="text-sm text-gray-700 line-clamp-2">
                    {{ licitacao.descricao }}
                  </div>
                </div>

                <button class="flex items-center justify-center">
                  <component
                    :is="
                      activeAccordion === licitacao.id ? ChevronUp : ChevronDown
                    "
                    class="w-6 h-6 text-gray-600"
                  />
                </button>
              </div>

              <!-- "Content" -->
              <div
                v-if="activeAccordion === licitacao.id"
                class="px-6 pb-4 pt-0"
              >
                <div class="grid gap-4">
                  <div>
                    <h4 class="text-sm font-semibold mb-2">Descrição</h4>
                    <p class="text-sm">{{ licitacao.descricao }}</p>
                  </div>

                  <div class="grid md:grid-cols-2 gap-4">
                    <div>
                      <h4 class="text-sm font-semibold">Edital a partir de:</h4>
                      <p class="text-sm">{{ licitacao.edital }}</p>
                    </div>

                    <div>
                      <h4 class="text-sm font-semibold">
                        Data de Entrega da Proposta:
                      </h4>
                      <p class="text-sm">{{ licitacao.dataEntregaProposta }}</p>
                    </div>
                  </div>

                  <div>
                    <h4 class="text-sm font-semibold">Endereço:</h4>
                    <p class="text-sm">{{ licitacao.endereco }}</p>
                  </div>

                  <div class="grid md:grid-cols-2 gap-4">
                    <div v-if="licitacao.telefone">
                      <h4 class="text-sm font-semibold">Telefone:</h4>
                      <p class="text-sm">{{ licitacao.telefone }}</p>
                    </div>

                    <div v-if="licitacao.fax">
                      <h4 class="text-sm font-semibold">Fax:</h4>
                      <p class="text-sm">{{ licitacao.fax }}</p>
                    </div>
                  </div>

                  <div class="flex justify-end">
                    <a
                      :href="licitacao.itemsUrl"
                      target="_blank"
                      class="flex items-center gap-2 border border-gray-300 px-4 py-2 text-sm rounded"
                    >
                      <svg
                        class="w-4 h-4"
                        xmlns="http://www.w3.org/2000/svg"
                        fill="none"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                      >
                        <path
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          d="M12 4v16m8-8H4"
                        />
                      </svg>
                      Itens e Download
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="flex justify-between items-center mt-10">
        <button
          class="px-4 py-2 bg-gray-200 text-gray-700 rounded hover:bg-gray-300"
          :class="{
            'cursor-not-allowed opacity-50': currentPage === 0,
            'cursor-pointer': currentPage !== 0,
          }"
          :disabled="currentPage === 0"
          @click="goToPreviousPage"
        >
          Anterior
        </button>

        <span class="text-sm">
          Página {{ currentPage + 1 }} de {{ totalPages }}
        </span>

        <button
          class="px-4 py-2 bg-gray-200 text-gray-700 rounded hover:bg-gray-300"
          :class="{
            'cursor-not-allowed opacity-50': currentPage === totalPages - 1,
            'cursor-pointer': currentPage !== totalPages - 1,
          }"
          :disabled="currentPage === totalPages - 1"
          @click="goToNextPage"
        >
          Próximo
        </button>
      </div>
    </div>
  </div>
</template>
