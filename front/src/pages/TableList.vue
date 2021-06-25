<template>
    <div class="row">
      <div class="col-12">
        <card :title="bullet.title">
          <div class="table-responsive">
            <base-table :data="bullet.data"
                        :columns="bullet.columns"
                        thead-classes="text-primary">
            </base-table>
          </div>
        </card>
      </div>

    </div>
</template>
<script>
import { BaseTable } from "@/components";
const tableColumns = ["no", "title", "writer", "create", "update"];
const tableData = [
  {
    no: 1,
    title: "Oud-Turnhout",
    writer: "Dakota Rice",
    create: "$36.738",
    update: "Niger",
  },
  {
    no: 2,
    title: "Sinaai-Waas",
    writer: "Minerva Hooper",
    create: "$23,789",
    update: "Curaçao",
  },
  {
    no: 3,
    title: "Baileux",
    writer: "Sage Rodriguez",
    create: "$56,142",
    update: "Netherlands",
  },
  {
    no: 4,
    title: "Overland Park",
    writer: "Philip Chaney",
    create: "$38,735",
    update: "Korea, South",
  },
  {
    no: 5,
    title: "Feldkirchen in Kärnten",
    writer: "Doris Greene",
    create: "$63,542",
    update: "Malawi",
  },
  {
    no: 6,
    title: 'Gloucester',
    writer: 'Mason Porter',
    create: '$98,615',
    update: 'Chile',
  },
  {
    no: 7,
    title: 'Gloucester',
    writer: 'Jon Porter',
    create: '$78,615',
    update: 'Portugal',
  }
];

export default {
  components: {
    BaseTable
  },
  data() {
    return {
      bullet: {
        title: "건의 게시판",
        columns: [...tableColumns],
        data: [...tableData]
      },
    };
  }
};
</script>
<style>
.table-responsive {
  overflow: hidden;
}
</style>
