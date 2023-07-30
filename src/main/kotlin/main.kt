import WallService.posts

data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val ownerName: String = "Pavel",
    val date: Int = 11072023,
    val text: String = "kotlin is cool",
    val createdBy: Int = 0,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isFavourite: Boolean = true,
    val copyright: String = "My Post",
    val isPinned: Boolean = true,
    var likes: Likes = Likes(),
    var comments: Comments?,
    val attachment: Attachment?,
    val friendsOnly: Boolean = true,
    var reposts: Reposts = Reposts(),
    var views: Views = Views(),
    val postType: String = "post",
    val markedAsAds: Boolean = false,
    var geo: Geo = Geo()
)
class PostNotFoundException(message: String) : Exception(message)
data class Geo (
    val type: String = "test geo",
    val coordinates: String = "test coordinates"
)
data class Views (
    val count: Int = 0
)
data class Reposts (
    val count: Int = 0,
    val userReposted: Boolean = false
)
data class Likes (
    val count: Int = 0,
    val userLikes: Boolean = true,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)
data class Comments (
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true,
    val text: String
)
object WallService {

    var posts = emptyArray<Post>()
    var comments = emptyArray<Comments>()
    private var lastId = 0


    fun createComment(postId: Int, comment: Comments): Comments {
        val commentedPost = posts.find { it.id == postId }
        if(commentedPost == null) {
            throw PostNotFoundException("Post with ID $postId not found")
        } else {
            val newComment = comment.copy(comments.size + 1)
            comments += newComment
            return newComment
        }
    }
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
    fun printAllComments() {
        for (comment in comments) {
            println(comment)
        }
    }
}



fun main() {
    WallService.add(Post(attachment = null, comments = null))
    WallService.add(Post(attachment = null, comments = null))
    WallService.printAllPosts()
    WallService.createComment(2, comment = Comments(text = "hi"))
    WallService.printAllComments()
    WallService.createComment(2, comment = Comments(text = "bye"))
    WallService.printAllComments()



}
