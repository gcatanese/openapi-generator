//
// AdditionalPropertiesClass.swift
//
// Generated by openapi-generator
// https://openapi-generator.tech
//

import Foundation

@objcMembers public class AdditionalPropertiesClass: NSObject, Codable, ParameterConvertible {

    public var mapString: [String: String]?
    public var mapMapString: [String: [String: String]]?

    public init(mapString: [String: String]? = nil, mapMapString: [String: [String: String]]? = nil) {
        self.mapString = mapString
        self.mapMapString = mapMapString
    }

    public enum CodingKeys: String, CodingKey, CaseIterable {
        case mapString = "map_string"
        case mapMapString = "map_map_string"
    }

    // Encodable protocol methods

    public func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encodeIfPresent(mapString, forKey: .mapString)
        try container.encodeIfPresent(mapMapString, forKey: .mapMapString)
    }
}

