import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {
    @Before
    fun clearBeforeTest(){
        WallService.clear()
    }

    @Test
    fun testAdd() {
        val post = Post(1, 1, likes = Likes(10), comments = null, attachment = FileAttachment(file = File(1, 1, "test", "testUrl")))
        WallService.add(post)
        val result = WallService.posts.size

        assertEquals(1, result)
    }



    @Test
    fun testUpdateExistingPost() {
        val existingPost = Post(1, 1, likes = Likes(5), comments = null, attachment = FileAttachment(file = File(1, 1, "test", "testUrl")))
        WallService.posts = arrayOf(existingPost)

        val updatedPost = Post(1, 1, likes = Likes(10), comments = null, attachment = FileAttachment(file = File(1, 1, "test", "testUrl")))
        val result = WallService.update(updatedPost)

        assertTrue(result)
        assertEquals(updatedPost, WallService.posts[0])
        }

    @Test
    fun testUpdateNonExistingPost() {
        val nonExistingPost = Post(2, 2, likes = Likes(5), comments = null, attachment = FileAttachment(file = File(1, 1, "test", "testUrl")))
        val result = WallService.update(nonExistingPost)

        assertFalse(result)
    }
}
