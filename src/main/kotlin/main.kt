data class Post(
    val id: Int,
    val ownerName: String,
    val date: Int,
    val text: String,
    val createdBy: Int,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isFavourite: Boolean,
    var likes: Likes
    )

data class Likes (
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean,
    val canPublish: Boolean
)

object WallService {
    private var posts = emptyArray<Post>()
    fun add(post: Post): Post {
        posts += post
            return posts.last()
    }
    fun update (post: Post): Boolean {

    }

}
fun main() {
    val post = Post(1,"Pavel Dubenko", 1_10_2000, "Nice", 1, true, true, true, true, Likes(1, true,true,true)
    )
    WallService.add(post)

}

