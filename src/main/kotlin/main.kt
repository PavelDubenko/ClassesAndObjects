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
    val copyright: String = "My Post",
    val isPinned: Boolean = true,
    var likes: Likes,
    var comments: Comments?
)

data class Likes (
    val count: Int,
    val userLikes: Boolean = true,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)
data class Comments (
    val count: Int,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)
object WallService {

    var posts = emptyArray<Post>()
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

    WallService.add(Post(1, likes = Likes(10), comments = null))
    WallService.add(Post(2, likes = Likes(5), comments = null))
    WallService.printAllPosts()
    WallService.update(Post(1, likes = Likes(15), comments = null))
    WallService.printAllPosts()
    println(WallService.posts.size)

}
