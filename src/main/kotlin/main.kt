data class Post(
    val id: Int,
    val ownerId: Int = 1,
    val fromId: Int = 2,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 5,
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
    var likes: Likes = Likes(),
    var comments: Comments?,
    val attachment: Attachment,
    val friendsOnly: Boolean = true,
    var reposts: Reposts = Reposts(),
    var views: Views = Views(),
    val postType: String = "post",
    val markedAsAds: Boolean = false,
    var geo: Geo = Geo()
)
data class Geo (
    val type: String = "test geo",
    val coordinates: String = "test coordinates"
)
data class Views (
    val count: Int = 5
)
data class Reposts (
    val count: Int = 10,
    val userReposted: Boolean = false
)
data class Likes (
    val count: Int = 5,
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

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId, likes = post.likes.copy(), comments = post.comments?.copy())
        return posts.last()
    }

    fun update(post: Post): Boolean{
        for((index, oldPost) in posts.withIndex()) {
            if (oldPost.id == post.id) {
                posts[index] = post.copy(likes = post.likes.copy(), comments = post.comments?.copy())
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

    WallService.add(Post(1, likes = Likes(10), comments = null, attachment = FileAttachment(file = File(1, 1, "test", "testUrl"))))
    WallService.add(Post(2, likes = Likes(5), comments = null, attachment = FileAttachment(file = File(1, 1, "test", "testUrl"))))
    WallService.printAllPosts()
    WallService.update(Post(1, likes = Likes(15), comments = null, attachment = FileAttachment(file = File(1, 1, "test", "testUrl"))))
    WallService.printAllPosts()
    println(WallService.posts.size)

}
