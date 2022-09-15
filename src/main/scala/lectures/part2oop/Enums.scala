package lectures.part2oop

object Enums extends App {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    def openDocument(): Unit =
      println(if (this == READ) "opening document..." else "reading not allowed")
  }

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

  val somePermission: Permissions = Permissions.READ
  val allPermissions = PermissionsWithBits.values
  val readPermission = Permissions.valueOf("READ")

  println(somePermission)
  println(allPermissions)
  println(readPermission)
  // standard API
  val somePermissionsOrdinal = somePermission.ordinal
  println(somePermissionsOrdinal)


}
