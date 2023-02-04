package uk.meadowsoftware.googlebooksapi.bookcardview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import uk.meadowsoftware.googlebooksapi.R
import uk.meadowsoftware.googlebooksapi.repository.localmodel.Book

class BookCardAdapter: RecyclerView.Adapter<BookCardAdapter.BookCardViewholder>() {

    private var bookList: MutableList<Book> = mutableListOf()

    fun setBookList(list : List <Book>) {
        bookList.clear()
        bookList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCardViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_card_view_layout, parent, false)
        return BookCardViewholder(view)
    }

    override fun onBindViewHolder(holder: BookCardViewholder, position: Int) {
        holder.apply {
            bookList[position].let { book ->
                holder.title.text = book.title
                holder.authors.text = book.authors.joinToString(separator = ", ")
                holder.notes.visibility = View.GONE
                holder.binding.visibility = View.GONE
                Glide.with(holder.imageView)
                    .load(book.thumbnailUrl)
                    .override(SIZE_ORIGINAL)
                    .into(holder.imageView)
            }
        }
    }

    override fun getItemCount() = bookList.size

    class BookCardViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
        val authors = view.findViewById<TextView>(R.id.author)
        val notes = view.findViewById<TextView>(R.id.notes)
        val binding = view.findViewById<TextView>(R.id.binding)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
    }
}