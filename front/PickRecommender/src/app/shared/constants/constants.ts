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
  }

  public static readonly modelClassNames = {
    USER : 'User',
    USER_ROLE: 'UserRole',
    AUDITORIUM : 'Auditorium',
    BID : 'Bid',
    CINEMA : 'Cinema',
  };

  public static readonly State = {
    VIEW : 'view',
    EDIT : 'edit',
  }

  public static readonly RequestType = {
    ACPTDEC: 'Accept-Decline',
    ADD_REMOVE: 'ADD-REMOVE',
  }

  public static readonly ListType = {
    COMMON : 'Common',
    REQUEST_LIST: 'Request list'
  }

  public static readonly ReservationType = {
    THEATRE: 'Theatre',
    CINEMA: 'Cinema',
    ALLFORUSER: 'All_For_User',
    AFU_THEATRE: 'All_For_User_Theatre',
    AFU_CINEMA: 'All_For_User_Cinema',
    CREATE: 'Create'
  }

  public static readonly AuditoriumType = {
    THEATRE: 'Theatre',
    CINEMA: 'Cinema'
  }

  public static readonly FilterType = {
    CONTAINS: 'contains',
    STARTS: 'startsWith',
    ENDS: 'endsWith'
  }
}
