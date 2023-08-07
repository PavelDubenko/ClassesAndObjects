data class Note(
    val id:Int = 0,
    val title: String = "Title",
    val text: String = "text",
    var comments: Comments? = null
)
class NoteNotFoundException(message: String) : Exception(message)

object NoteService {
    var notes = mutableListOf<Note>()
    var comments = mutableListOf<Comments>()
    var lastNoteId = 0
    var lastCommentId = 0

    fun addComment(noteId:Int, comment: Comments): Comments{
        val commentedNote = notes.find { it.id == noteId }
        if (commentedNote == null) {
            throw NoteNotFoundException("Note with ID $noteId not found")
        } else {
            comments += comment.copy(count = comments.size + 1)
            notes += commentedNote.copy(comments = comment.copy(id = ++lastCommentId))
        }
        return comment
    }


    fun addNote(note: Note):Note {
        notes += note.copy(id = ++lastNoteId)
        return notes.last()
    }
    fun deleteNote(id: Int) {
        val deletedNote = notes.find { it.id == id }
        if (deletedNote == null) {
            throw NoteNotFoundException("Note with id $id not found")
        } else {
            notes.remove(deletedNote)
        }
    }
    fun updateNote(note: Note): Boolean {
        for ((index, oldNote) in notes.withIndex()){
            if (oldNote.id == note.id) {
                notes[index] = note.copy()
                return true
            }
        }
        return false
    }

    fun printAllNotes(){
        for (note in notes)
            println(note)
    }
}