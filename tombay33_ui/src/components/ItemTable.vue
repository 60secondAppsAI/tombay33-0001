
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3">
      <div class="col-8"></div>
      <div class="col-4">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Items</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalItems = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalItems">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add Item</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="ItemId" type="text" placeholder="Enter ItemId" v-model="itemToAdd.itemId"></base-input>
  <base-input label="Title" type="text" placeholder="Enter Title" v-model="itemToAdd.title"></base-input>
  <base-input label="Description" type="text" placeholder="Enter Description" v-model="itemToAdd.description"></base-input>
  <base-input label="StartPrice" type="text" placeholder="Enter StartPrice" v-model="itemToAdd.startPrice"></base-input>
  <base-input label="CurrentBid" type="text" placeholder="Enter CurrentBid" v-model="itemToAdd.currentBid"></base-input>
  <base-input label="AuctionEndDate" type="text" placeholder="Enter AuctionEndDate" v-model="itemToAdd.auctionEndDate"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="items" :row-key="record => record.ItemId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <ItemPictureView :items="items" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/Inputs/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/index";
import ItemService from "../services/ItemService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import ItemPictureView from './ItemPictureView.vue';


const itemsColumns = [
  "itemId",
  "year",
  "date",
  "competitionId",
  "itemId"
]

export default {
  props: {
    items: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    ItemPictureView
  },

  data() {
    return {
      modalItems: false,
        isTableView: true,

      columns: [
        {
          title: 'Item Id',
		dataIndex: 'itemId',
          visible: true,
          scopedSlots: { customRender: 'itemId' },
          sorter: true
          //sorter: (a, b) => a.itemId - b.itemId,
          //sorter: (a, b) => a.itemId.localeCompare(b.itemId),
        },
        {
          title: 'Title',
		dataIndex: 'title',
          visible: true,
          scopedSlots: { customRender: 'title' },
          sorter: true
          //sorter: (a, b) => a.title - b.title,
          //sorter: (a, b) => a.title.localeCompare(b.title),
        },
        {
          title: 'Description',
		dataIndex: 'description',
          visible: true,
          scopedSlots: { customRender: 'description' },
          sorter: true
          //sorter: (a, b) => a.description - b.description,
          //sorter: (a, b) => a.description.localeCompare(b.description),
        },
        {
          title: 'Start Price',
		dataIndex: 'startPrice',
          visible: true,
          scopedSlots: { customRender: 'startPrice' },
          sorter: true
          //sorter: (a, b) => a.startPrice - b.startPrice,
          //sorter: (a, b) => a.startPrice.localeCompare(b.startPrice),
        },
        {
          title: 'Current Bid',
		dataIndex: 'currentBid',
          visible: true,
          scopedSlots: { customRender: 'currentBid' },
          sorter: true
          //sorter: (a, b) => a.currentBid - b.currentBid,
          //sorter: (a, b) => a.currentBid.localeCompare(b.currentBid),
        },
        {
          title: 'Auction End Date',
		dataIndex: 'auctionEndDate',
          visible: true,
          scopedSlots: { customRender: 'auctionEndDate' },
          sorter: true
          //sorter: (a, b) => a.auctionEndDate - b.auctionEndDate,
          //sorter: (a, b) => a.auctionEndDate.localeCompare(b.auctionEndDate),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} items`,
      },

      items: [],
      itemToAdd: {},

      itemsTable: {
        columns: [...itemsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'itemId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderItemsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let itemsTableData = [];
      for (let i = 0; i < this.items.length; i++) {
        itemsTableData.push({
          id: i,
          itemId: this.items[i].itemId,
          year: this.items[i].year,
          date: this.items[i].date,
          competitionId: this.items[i].competitionId,
          itemId: this.items[i].itemId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-items',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToUserDetail(id) {
      this.$router.push({ name: 'UserDetail', params: { userId: id.toString() }})
    },
    routingToItemDetail(id) {
      this.$router.push({ name: 'ItemDetail', params: { itemId: id.toString() }})
    },
    routingToBidDetail(id) {
      this.$router.push({ name: 'BidDetail', params: { bidId: id.toString() }})
    },
    routingToCategoryDetail(id) {
      this.$router.push({ name: 'CategoryDetail', params: { categoryId: id.toString() }})
    },
    routingToItemCategoryDetail(id) {
      this.$router.push({ name: 'ItemCategoryDetail', params: { itemCategoryId: id.toString() }})
    },
    routingToFeedbackDetail(id) {
      this.$router.push({ name: 'FeedbackDetail', params: { feedbackId: id.toString() }})
    },
    routingToPaymentDetail(id) {
      this.$router.push({ name: 'PaymentDetail', params: { paymentId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForItemsChanged', this.searchQuery);
		//this.renderItemsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalItems = false;

      const currentDate = new Date().getTime();
      this.itemToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.itemToAdd);
      console.log(jsonData);
      
      const res = await ItemService.addItem(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderItemsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}

</style>
