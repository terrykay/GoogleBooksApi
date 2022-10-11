package uk.meadowsoftware.googlebooksapi.bookcardview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import uk.meadowsoftware.googlebooksapi.databinding.BookCardViewLayoutBinding

class BookCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    val viewBinding = BookCardViewLayoutBinding.inflate(LayoutInflater.from(context))

    var titleText: String
        set(value) {
            viewBinding.title.text = value
        }
        get() {
            return viewBinding.title.text.toString()
        }

    var bookImage: Drawable
        set(value) {
            viewBinding.imageView.setImageDrawable(value)
        }
        get() { return viewBinding.imageView.drawable }
}