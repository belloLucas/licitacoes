<script setup>
import { ref, defineEmits } from "vue";
import { Search } from "lucide-vue-next";

const pregaoFilter = ref("");
const uasgFilter = ref("");

const emit = defineEmits(["search"]);

const handleSearch = (e) => {
  e.preventDefault();

  const filters = {};
  if (pregaoFilter.value) {
    filters.pregao = pregaoFilter.value;
  }
  if (uasgFilter.value) {
    filters.uasg = uasgFilter.value;
  }

  emit("search", filters);
};
</script>

<template>
  <div class="bg-white p-4 rounded-lg shadow">
    <h2 class="text-lg font-medium mb-4">Filtros de Busca</h2>
    <form @submit="handleSearch" class="grid gap-4 md:grid-cols-[1fr_1fr_auto]">
      <div class="space-y-2">
        <label for="pregao" class="text-sm font-medium">
          Busca por Pregão
        </label>
        <input
          id="pregao"
          placeholder="Ex: 90079/2025"
          v-model="pregaoFilter"
          class="border rounded px-2 py-1 w-full border-gray-300 focus:outline-hidden focus:border-green-700 focus:ring-1 mt-1"
        />
      </div>
      <div class="space-y-2">
        <label for="uasg" class="text-sm font-medium"> Busca por UASG </label>
        <input
          id="uasg"
          placeholder="Ex: 250005"
          v-model="uasgFilter"
          class="border rounded px-2 py-1 w-full border-gray-300 focus:outline-hidden focus:border-green-700 focus:ring-1 mt-1"
        />
      </div>
      <div class="flex items-end">
        <button
          type="submit"
          class="bg-green-700 hover:bg-green-800 text-white px-4 py-2 rounded flex items-center"
        >
          <Search class="w-4 h-4 mr-2" />
          Buscar
        </button>
      </div>
    </form>
  </div>
</template>
