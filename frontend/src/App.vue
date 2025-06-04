<script setup>
import Header from "./components/Header.vue";
import LicitacoesList from "./components/LicitacoesList.vue";
import SearchFilters from "./components/SearchFilters.vue";

import { ref } from "vue";

const currentDate = new Date();
const formattedDate = currentDate.toLocaleDateString("pt-BR", {
  day: "2-digit",
  month: "2-digit",
  year: "numeric",
});

const filters = ref({});

const handleSearch = (newFilters) => {
  filters.value = newFilters;
};
</script>

<template>
  <Header />
  <main class="container mx-auto p-4">
    <SearchFilters @search="handleSearch" />
    <div class="mt-6">
      <h2 class="text-xl font-semibold mb-4">
        Licitações publicadas no Diário Oficial da União em {{ formattedDate }}
      </h2>
      <LicitacoesList :filters="filters" />
    </div>
  </main>
</template>

<style scoped></style>
