import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun testAdd() {
        val post = Post(1, "Test Post", likes = Likes(10), comments = null, attachment = FileAttachment(file = File(1, 1, "test", "testUrl")))
        WallService.add(post)
        val result = WallService.posts.size

        assertEquals(2, result)
    }

    @Test
    fun testUpdateExistingPost() {
        val existingPost = Post(1, "Existing Post", likes = Likes(5), comments = null, attachment = FileAttachment(file = File(1, 1, "test", "testUrl")))
        WallService.posts = arrayOf(existingPost)

        val updatedPost = Post(1, "Updated Post", likes = Likes(10), comments = null, attachment = FileAttachment(file = File(1, 1, "test", "testUrl")))
        val result = WallService.update(updatedPost)

        assertTrue(result)
        assertEquals(updatedPost, WallService.posts[0])
        }

    @Test
    fun testUpdateNonExistingPost() {
        val nonExistingPost = Post(2, "Non Existing Post", likes = Likes(5), comments = null, attachment = FileAttachment(file = File(1, 1, "test", "testUrl")))
        val result = WallService.update(nonExistingPost)

        assertFalse(result)
    }
}
