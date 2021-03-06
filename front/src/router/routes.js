import DashboardLayout from "@/layout/dashboard/DashboardLayout.vue";
// GeneralViews
import NotFound from "@/pages/NotFoundPage.vue";

// Admin pages
const Dashboard = () => import(/* webpackChunkName: "dashboard" */"@/pages/Dashboard.vue");
const Typography = () => import(/* webpackChunkName: "common" */ "@/pages/Typography.vue");
const TableList = () => import(/* webpackChunkName: "common" */ "@/pages/TableList.vue");
const Profile = () => import(/* webpackChunkName: "common" */ "@/pages/Profile.vue");
const Bullet = () => import(/* webpackChunkName: "common" */ "@/pages/Bullet");
const Write = () => import(/* webpackChunkName: "common" */ "@/pages/Write");

const routes = [
  {
    path: "/",
    component: DashboardLayout,
    redirect: "/dashboard",
    children: [
      {
        path: "dashboard",
        name: "dashboard",
        component: Dashboard
      },
      {
        path: "covid_info",
        name: "COVID-19 정보",
        component: Typography
      },
      {
        path: "bullet",
        name: "건의 게시판",
        component: TableList
      },
      {
        path: "profile",
        name: "개발자 프로필",
        component: Profile
      },
      {
        path: "bullet/:no",
        name: "상세 페이지",
        component: Bullet
      },
      {
        path: "write",
        name: "Write",
        component: Write
      },
    ]
  },
  { path: "*", component: NotFound },
];

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes;
