fun main() {
    val book = Book("Harry Porter", "J.K.Rowling", 20.0)
    val audioBook = EBook("Harry Porter", "J.K.Rowling", 20.0,"Harry-Porter.pdf", "Harry-Porter.epub")
    book.read()
    audioBook.read()
    println(audioBook.title);
}

open class Book(val title: String, val author: String, val price: Double) {
    open fun read() {
        println("Reading Paper book")
    }
}

class EBook(title: String, author: String, price: Double,
            val pdf: String,
            val epub: String) : Book(title = title, author = author, price = price) {
    override fun read() {
        println("Read from Electronic Device")
    }
}
