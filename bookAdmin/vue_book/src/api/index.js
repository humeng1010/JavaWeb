import requests from './request';

export const selectAll = () => requests({ method: "GET", url: "/selectAll" })

export const addBrand = (brand) => requests({ method: "POST", url: "/addBrand", data: brand })

export const updateBrand = (brand) => requests({ method: "POST", url: "/updateBrand" })