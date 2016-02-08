package info.githubcompare.json

import info.githubcompare.model.Event
import spray.json._

object EventJsonProtocol extends DefaultJsonProtocol {
  implicit object EventJsonFormat extends RootJsonFormat[Event] {
    override def write(e: Event) = throw new NotImplementedError("Serialization not implemented")

    override def read(value: JsValue): Event = {
      value.asJsObject.getFields("type", "repo", "created_at") match {
        case Seq(JsString(typ), JsObject(repoProperties), JsString(date)) => repoProperties.get("name") match {
          case Some(JsString(name)) => new Event(typ, name, date)
          case _ => throw new DeserializationException(s"Error deserializing $value")
        }
        case _ => throw new DeserializationException(s"Error deserializing $value")
      }
    }
  }
}