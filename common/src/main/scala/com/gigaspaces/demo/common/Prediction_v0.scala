package com.gigaspaces.demo.common

import org.insightedge.scala.annotation._
import scala.beans.{BeanProperty, BooleanBeanProperty}

@SpaceClass
case class Prediction_v0(
                        @BeanProperty
                        @SpaceId
                        var id: String,

                        @BeanProperty
                        var label: String,

                        @BeanProperty
                        var cluster: Int
                      ) {
  def this() = this(null, null, -1)
}