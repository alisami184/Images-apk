package com.example.galle


data class ImageInfo(
    val resourceId: Int,
    val author: String,
    val date: String,
    val description: String
)

object  ImageData
{
    val images = listOf(
        ImageInfo(R.drawable.image, "Saint Thomas", "1850","église"),
        ImageInfo(R.drawable.image1, "Le Grand", "2005","théatre paris"),
        ImageInfo(R.drawable.cathedrale, " Cathédrale de Strasbourg", "1015","La cathédrale de Strasbourg est un chef-d'œuvre de l'architecture médiévale. A ne pas manquer avec les enfants"),
        ImageInfo(R.drawable.basillique, "Basilique Notre-Dame de la Garde", "1853","De Notre-Dame de la Garde on admire le splendide panorama sur la ville de Marseille et la mer. La basilique revêt des mosaïques qui valent le détour et la hauteur de la Vierge est étonnante: près de 24 m de haut."),
        ImageInfo(R.drawable.sainte, "La Sainte Chapelle", "1248","La Sainte-Chapelle est un pur joyau paré de vitraux éblouissants, à Paris. Louis IX la fit construire pour y placer les reliques de la Passion du Christ, qui n'y sont plus, mais leur écrin est une merveille: "),

    )
}
