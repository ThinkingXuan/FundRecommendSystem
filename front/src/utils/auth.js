import Cookies from 'js-cookie'

const Name = 'vue_admin_template_name'

export function getName() {
  return Cookies.get(Name)
}

export function setName(name) {
  return Cookies.set(Name, name)
}

export function removeName() {
  return Cookies.remove(Name)
}
