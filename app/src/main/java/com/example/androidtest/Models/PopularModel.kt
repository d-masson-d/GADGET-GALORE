package com.example.androidtest.Models



class PopularModel{
    private var rvImage: Int? = null
    private var rvName: String = ""
    private var rvPrice: String =""
    private var countRv: Int = 1

    constructor()
    constructor(rvImage: Int?, rvName: String, rvPrice: String,countRv: Int){
        this.rvName = rvName
        this.rvImage = rvImage
        this.rvPrice = rvPrice
        this.countRv = countRv
    }

    fun getCountRv(): Int{
        return countRv
    }

    fun getRvImage(): Int?{
        return rvImage
    }

    fun getRvName(): String {
        return rvName
    }

    fun getRvPrice(): String {
        return rvPrice
    }

    fun setCountRv(countRv: Int){
        this.countRv = countRv
    }

    fun setRvImage(rvImage: Int?){
        this.rvImage = rvImage
    }

    fun setRvName(rvName: String){
        this.rvName = rvName
    }

    fun setRvPrice(rvPrice: String){
        this.rvPrice = rvPrice
    }


}