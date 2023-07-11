data class Post(
    val id: Int,
    val ownerName: String = "Pavel",
    val date: Int = 11072023,
    val text: String = "kotlin is cool",
    val createdBy: Int = 1,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isFavourite: Boolean = true,
    var likes: Likes
    )

data class Likes (
    val count: Int,
    val userLikes: Boolean = true,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

object WallService {

    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId, likes = post.likes.copy())
            return posts.last()
    }

    fun update(post: Post): Boolean{
        for((index, oldPost) in posts.withIndex()) {
            if (oldPost.id == post.id) {
                posts[index] = post.copy(likes = post.likes.copy())
                return true
            }
        }
        return false
    }

   fun printAllPosts () {
        for(post in posts) {
            println(post)
        }
    }

}
fun main() {

    WallService.add(Post(1, likes = Likes(10)))
    WallService.add(Post(2, likes = Likes(5)))
    WallService.printAllPosts()
    WallService.update(Post(1, likes = Likes(15)))
    WallService.printAllPosts()

}

