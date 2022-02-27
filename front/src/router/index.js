import Vue from 'vue'
import Router from 'vue-router'
const tiantian_fund = () => import('@/views/fund/tiantian_fund')
const danjuan_fund = () => import('@/views/fund/danjuan_fund')
Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [

  {
    path: '/home',
    component: Layout,
    redirect: '/home/fund_couplr',
    meta: { title: '基金', icon: 'el-icon-set-up' },
    children: [
      {
        path: 'fund_couplr',
        name:'home',
        component: (params) => import('@/views/fund/list'),
        meta: { title: '基金组合', icon: 'el-icon-s-marketing' },

      },
      {
        path: 'fund_advice',
        name:'home',
        component: (params) => import('@/views/fund/advice'),
        meta: { title: '基金推荐', icon: 'el-icon-success' },

      },
      {
        path: 'tiantian_fund/:plan_code',
        component: tiantian_fund,
        hidden: true
      },
      {
        path: 'danjuan_fund/:plan_code',
        component: danjuan_fund,
        hidden: true
      },
    ]
  },
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/login/register'),
    hidden: 'abc'=='abc'
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require fabservice support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
