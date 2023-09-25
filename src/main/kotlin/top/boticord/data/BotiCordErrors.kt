package top.boticord.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BotiCordErrors(val code: Int) {
    @SerialName("0") UnknownError(0),
    @SerialName("1") InternalServerError(1),
    @SerialName("2") RateLimited(2),
    @SerialName("3") NotFound(3),
    @SerialName("4") Forbidden(4),
    @SerialName("5") BadRequest(5),
    @SerialName("6") Unauthorized(6),
    @SerialName("7") RPCError(7),
    @SerialName("8") WSError(8),
    @SerialName("9") ThirdPartyFail(9),
    @SerialName("10") UnknownUser(10),
    @SerialName("11") ShortDomainTaken(11),
    @SerialName("12") UnknownShortDomain(12),
    @SerialName("13") UnknownLibrary(13),
    @SerialName("14") TokenInvalid(14),
    @SerialName("15") UnknownResource(15),
    @SerialName("16") UnknownTag(16),
    @SerialName("17") PermissionDenied(17),
    @SerialName("18") UnknownComment(18),
    @SerialName("19") UnknownBot(19),
    @SerialName("20") UnknownServer(20),
    @SerialName("21") UnknownBadge(21),
    @SerialName("22") UserAlreadyHasABadge(22),
    @SerialName("23") InvalidInviteCode(23),
    @SerialName("24") ServerAlreadyExists(24),
    @SerialName("25") BotNotPresentOnQueueServer(25),
    @SerialName("26") UnknownUp(26),
    @SerialName("27") TooManyUps(27),
    @SerialName("28") InvalidStatus(28),
    @SerialName("29") UnknownReport(29),
    @SerialName("30") UnsupportedMediaType(30),
    @SerialName("31") UnknownApplication(31),
    @SerialName("32") AutomatedRequestsNotAllowed(32)
}