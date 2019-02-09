package at.fh.swengb.gruber.homeexercise2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_add_note.view.*
import kotlinx.android.synthetic.main.notelayout.view.*

class noteAdapter(val clickListener: (note: note) -> Unit, val longClick: (note: note) -> Unit):RecyclerView.Adapter<noteView>() {
    private var noteList = mutableListOf<note>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): noteView {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.notelayout, parent, false)
        return noteView(itemView, clickListener, longClick)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(viewHolder: noteView, position: Int) {
        val noteElem = noteList[position]
        viewHolder.bindData(noteElem)
    }

    fun updateNote(updates: List<note>){
        noteList = updates.toMutableList()
        notifyDataSetChanged()
    }

}

class noteView(v:View, val clickListener: (note: note) -> Unit, val longClick: (note: note) -> Unit):RecyclerView.ViewHolder(v){
    fun bindData(note: note){
        itemView.noteTitleRecyclerView.text = note.title
        itemView.noteContentRecyclerView.text = note.content
        itemView.setOnClickListener {
            clickListener(note)
        }
        itemView.setOnLongClickListener {
            longClick(note)
            true
        }
    }
}