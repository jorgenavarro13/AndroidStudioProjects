package com.example.listofcontacts.model

import kotlin.math.min

data class Contact(
    val id : Int,
    val name : String,
    val lastname : String,
    val phone : String,
    val email : String,
    val address : String?,
    val enterprise : String?
)

fun getContacts(): List<Contact> = listOf(
    Contact(1, "Juan", "Valentin Arenas", "4961552910", "juanvalentinarenas@gmail.com", "Cerro de la Conformidad 2709", "John Deere"),
    Contact(2, "Jorge Arturo", "Montiel Navarro", "7713266491", "jorge.85navarro@gmail.com", "Cerro del Fraile 2740", null),
    Contact(3, "Sylvie Aylin", "Zabetoglu", "871426588", "a01234692@gmail.com", null, null),
    Contact(4, "Travesti Nicté", "Nicté", "7711007036", "a01278821@tec.mx", null, null),
    Contact(5, "Penelope", "Negrete", "12345679", "penelopenegrete@gmail.com", null, null),
    Contact(6, "Carlos Eduardo", "Mendoza Ruiz", "5512345678", "carlos.mendoza@outlook.com", "Av. Insurgentes Sur 1450", "Mabe"),
    Contact(7, "Ana Valeria", "Gómez Palacio", "8119876543", "valeria.gomez@gmail.com", "Paseo de los Leones 402", null),
    Contact(8, "Luis Fernando", "Castillo Torres", "3334567890", "lfernando_castillo@hotmail.com", null, "Bimbo"),
    Contact(9, "María Elena", "Villarreal Soto", "4491122334", "maria.villarreal@tec.mx", "Sierra de Picachos 112", null),
    Contact(10, "Alejandro", "Rojas Benítez", "6645566778", "alex.rojas@gmail.com", null, null),
    Contact(11, "Sofía Isabel", "Morales Luna", "2223344556", "sofia.morales@yahoo.com", "Calle de la Amargura 45", "Cemex"),
    Contact(12, "Ricardo", "Gutiérrez Marín", "9998877665", "ricardo.gutierrez@live.com", "Av. Colón 890", null),
    Contact(13, "Gabriela", "Paredes Cruz", "7441234567", "gaby.paredes@gmail.com", null, "Telcel"),
    Contact(14, "Mauricio", "Salgado Ortiz", "8334455667", "mauricio.salgado@gmail.com", "Lomas del Valle 231", null),
    Contact(15, "Daniela", "Sánchez Herrera", "4775566778", "daniela.sanchez@hotmail.com", null, null),
    Contact(16, "Héctor Alfonso", "Domínguez Vega", "6149988776", "hector.dominguez@uabc.mx", "Circuito de los Pintores 12", "Femsa"),
    Contact(17, "Paulina", "Cervantes Silva", "9514433221", "paulina.cervantes@gmail.com", "Av. Juárez 506", null),
    Contact(18, "Javier", "Villalobos Peña", "8447766554", "javier.villalobos@outlook.com", null, "Kia Motors"),
    Contact(19, "Andrea Celeste", "Ramos Delgado", "5551122334", "andrea.ramos@tec.mx", "Camino Real 88", null),
    Contact(20, "Roberto", "Zúñiga Castro", "3318899001", "roberto.zuniga@gmail.com", null, null)
)

fun calculateAbreviationContactName(name: String , lastName:String ) : String {
    var index :Int = 0
    var result :String = ""
    var nameAbreviation :String =""
    var lastNameAbreviation : String =""
    val upperCaseName = name.trim().uppercase()
    println(upperCaseName)
    for(l in upperCaseName){
        if(l == ' '){index=0; continue;}
        if(index==0){nameAbreviation+=l;}
        index+=1;
    }

    index=0;
    val upperCaseLastName = lastName.trim().uppercase()
    println(upperCaseLastName)
    for(l in upperCaseLastName){
        if(l == ' '){index=0; continue;}
        if(index==0 ){lastNameAbreviation+=l;}
        index+=1;
    }

    if(lastNameAbreviation.length>=1 && nameAbreviation.length>=1){
        result="${nameAbreviation[0]}${lastNameAbreviation[0]}"
    }else{
        if(lastNameAbreviation.length==0){
            val limit = min(nameAbreviation.length, 2)
            for(i in 0 until limit){
                result +=nameAbreviation[i]
            }
        }
        else{
            val limit = min(lastNameAbreviation.length, 2)
            for(i in 0 until limit ){
                result +=lastNameAbreviation[i]}
        }
    }
    return result;
}
