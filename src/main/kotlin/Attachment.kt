interface Attachment  {
    val type: String
}
data class Audio (
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val duration: Int,
    val url: String
)
data class AudioAttachment(val audio: Audio): Attachment{
    override val type: String = "audio"
}
data class Video (
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val duration: Int,
    val url: String
)
data class VideoAttachment(val video: Video): Attachment {
    override val type: String = "video"
}
data class File(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val url: String
)
data class FileAttachment(val file: File): Attachment {
    override val type: String = "file"
}
data class Sticker(
    val productId: Int,
    val stickerID: Int,
    val animationUrl: String
)
data class StickerAttachment(val sticker: Sticker): Attachment {
    override val type: String = "sticker"
}
data class Gift(
    val id: Int,
    val thumb256: String,
    val thumb96: String,
    val thumb48: String
)
data class GiftAttachment(val gift: Gift): Attachment {
    override val type: String = "gift"
}