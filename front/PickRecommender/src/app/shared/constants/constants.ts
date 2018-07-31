export class Constants {
  public static readonly HttpMethods = {
    GET: 'GET',
    POST: 'POST',
    PUT: 'PUT',
    DELETE: 'DELETE',
    OPTIONS: 'OPTIONS',
    HEAD: 'HEAD',
  };

  public static readonly messageType = {
    WARN : 'WARNING',
    ERR : 'ERROR',
    SUCC : 'SUCCESS',
  };

  public static readonly State = {
    VIEW : 'view',
    EDIT : 'edit',
  };

  public static readonly RequestType = {
    ACPTDEC: 'Accept-Decline',
    ADD_REMOVE: 'ADD-REMOVE',
  };

  public static readonly ListType = {
    COMMON : 'Common',
    REQUEST_LIST: 'Request list'
  };

  public static readonly FilterType = {
    CONTAINS: 'contains',
    STARTS: 'startsWith',
    ENDS: 'endsWith'
  };

  public static readonly positions = {
    'top' : ['Top', 'Position1'],
    'jg' : ['Jungle', 'Position2'],
    'mid': ['Mid', 'Position3'],
    'bot' : ['Bottom', 'Position4'],
    'sup' : ['Support', 'Position5'],
  };
}
