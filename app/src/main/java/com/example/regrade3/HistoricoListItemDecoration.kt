package com.example.regrade3
import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HistoricoListItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val space = context.resources.getDimensionPixelSize(R.dimen.simple_margin)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
        outRect.top = space
    }
}