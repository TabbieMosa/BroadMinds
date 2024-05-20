package com.example.broadminds.models

class Upload{
    var name:String=""
    var email:String=""
    var ideadescription:String=""
    var imageUrl:String=""
    var id:String=""

    constructor(name:String,email:String,ideaDescription:String,imageUrl:String,id:String){

        this.name=name
        this.email=email
        this.ideadescription=ideaDescription
        this.imageUrl=imageUrl
        this.id=id

    }
    constructor()
}